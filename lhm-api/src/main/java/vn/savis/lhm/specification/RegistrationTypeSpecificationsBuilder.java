package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.RegistrationType;


public class RegistrationTypeSpecificationsBuilder {
	private final List<SearchCriteria> params;

    public RegistrationTypeSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public RegistrationTypeSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<RegistrationType> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<RegistrationType>> specs = new ArrayList<Specification<RegistrationType>>();
        for (SearchCriteria param : params) {
            specs.add(new RegistrationTypeSpecification(param));
        }

        Specification<RegistrationType> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
    public final RegistrationTypeSpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
