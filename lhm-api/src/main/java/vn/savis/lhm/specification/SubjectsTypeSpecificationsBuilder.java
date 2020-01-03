package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.SubjectsType;



public class SubjectsTypeSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public SubjectsTypeSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public SubjectsTypeSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<SubjectsType> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<SubjectsType>> specs = new ArrayList<Specification<SubjectsType>>();
        for (SearchCriteria param : params) {
            specs.add(new SubjectsTypeSpecifications(param));
        }

        Specification<SubjectsType> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
    public final SubjectsTypeSpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}

