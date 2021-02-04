
package com.toprate.hr_tek_demo.repository.specification.base;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 10:20 29/01/2021
 */
public abstract class BaseQuerySpecification<E> {

    public Specification<E> initWhere() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.conjunction();
        };
    }

    protected String wrapLikeQuery(Object value) {
        return "%" + String.valueOf(value).toUpperCase() + '%';
    }

    //like
    public Specification<E> likeSpecification(String idField, Object value) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(idField)), wrapLikeQuery(value));
    }

    //equal
    protected Specification<E> equalsSpecification(String idField, Object value) {
        return (root, query, builder) -> builder.equal(root.get(idField), value);
    }

    protected <X> Specification<E> inSpecification(Function<Root<E>, Expression<X>> metaclassFunction,
                                                   final Collection<X> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<X> in = builder.in(metaclassFunction.apply(root));
            for (X value : values) {
                in = in.value(value);
            }
            return in;
        };
    }

    //join one to many
    public <R, J, F extends Comparable<? super F>> Specification<E> buildJoinSpecification(Filter<F> filter, SingularAttribute<? super E, R> reference, SingularAttribute<J, F> valueField) {
        Specification<E> result = null;
        if (filter.getEquals() != null) {
            result = this.equalsOfJoint(reference, valueField, filter.getEquals());
        }
        return result;
    }

    //filter equals
    private <R, J, F> Specification<E> equalsOfJoint(SingularAttribute<? super E, R> reference, SingularAttribute<J, F> idField, F value) {
        return (root, query, builder) -> builder.equal(root.join(reference).get(idField.getName()), value);
    }

    //join many to many
    public <R, J, F extends Comparable<? super F>> Specification<E> buildJoinSpecification(Filter<F> filter, ListAttribute<? super E, R> reference, SingularAttribute<R, J> joinField, SingularAttribute<J, F> valueField) {
        Specification<E> result = null;
        if (filter.getEquals() != null) {
            result = this.equalsOfJoint(reference, joinField, valueField, filter.getEquals());
        }
        if (filter.getIn() != null) {
            result = this.valueInOfJoint(reference, joinField, valueField, filter.getIn());
        }
        return result;
    }

    //filter equals
    private <R, J, F> Specification<E> equalsOfJoint(ListAttribute<? super E, R> reference, SingularAttribute<R, J> joinField, SingularAttribute<J, F> idField, F value) {
        return (root, query, builder) -> builder.equal(root.join(reference).join(joinField).get(idField), value);
    }

    //filter in
    private <R, J, F> Specification<E> valueInOfJoint(ListAttribute<? super E, R> reference, SingularAttribute<R, J> joinField, SingularAttribute<J, F> valueField, Collection<F> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<F> in = builder.in(root.join(reference).join(joinField).get(valueField));
            for (F value : values) {
                in = in.value(value);
            }
            return in;
        };
    }
}
