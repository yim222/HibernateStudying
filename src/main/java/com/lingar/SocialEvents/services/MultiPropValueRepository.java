package com.lingar.SocialEvents.services;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lingar.SocialEvents.entities.MultiPropLvl1Value;
import com.lingar.SocialEvents.entities.MultiPropLvl2Value;

public interface MultiPropValueRepository extends PagingAndSortingRepository<MultiPropLvl1Value, Long>{

}
