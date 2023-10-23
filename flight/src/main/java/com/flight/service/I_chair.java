package com.flight.service;

import java.util.List;
import java.util.Optional;

import com.flight.entity.chair;
import com.flight.reponse.chair_reponse;
import com.flight.reponse.getdata.chair_data;
import com.flight.request.orther.sizetypechair;

public interface I_chair {
	public void create_chair(Optional<List<sizetypechair>> list_type, int size , Long idplane);
	public void update_chair(Optional<List<sizetypechair>> list_type, int size , Long idplane);
	public void delete_chair(Long idplane);
	public chair_reponse get(Long idchair);
}
