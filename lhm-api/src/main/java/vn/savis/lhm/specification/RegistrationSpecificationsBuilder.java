package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.Registration;


public class RegistrationSpecificationsBuilder {
	private final List<SearchCriteria> params;

    public RegistrationSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public RegistrationSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Registration> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Registration>> specs = new ArrayList<Specification<Registration>>();
        for (SearchCriteria param : params) {
            specs.add(new RegistrationSpecifications(param));
        }

        Specification<Registration> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
    public final RegistrationSpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
