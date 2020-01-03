package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;


import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.Faculty;
import vn.savis.lhm.entity.Lecturers;

public class LecturersSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public LecturersSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public LecturersSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Lecturers> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Lecturers>> specs = new ArrayList<Specification<Lecturers>>();
        for (SearchCriteria param : params) {
            specs.add(new LecturersSpecifications(param));
        }

        Specification<Lecturers> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
    public final LecturersSpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
