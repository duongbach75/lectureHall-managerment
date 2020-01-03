package vn.savis.lhm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.savis.lhm.dto.SearchCriteria;
import vn.savis.lhm.entity.StaffIncharge;


public class StaffInchargeSpecificationsBuilder {
	private final List<SearchCriteria> params;

    public StaffInchargeSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public StaffInchargeSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<StaffIncharge> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<StaffIncharge>> specs = new ArrayList<Specification<StaffIncharge>>();
        for (SearchCriteria param : params) {
            specs.add(new StaffInchargeSpecification(param));
        }

        Specification<StaffIncharge> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
    public final StaffInchargeSpecificationsBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
