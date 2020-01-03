package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.Faculty;



public class FacultySpecificationsBuilder {

    private final List<SearchCriteria> params;

    public FacultySpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public FacultySpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Faculty> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Faculty>> specs = new ArrayList<Specification<Faculty>>();
        for (SearchCriteria param : params) {
            specs.add(new FacultySpecifications(param));
        }

        Specification<Faculty> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
    public final FacultySpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
