package com.lams.api.service.master;

import com.lams.model.bo.AddressBO;

public interface AddressService {

	public void saveAddress(AddressBO addressBO,Long userId);
}
