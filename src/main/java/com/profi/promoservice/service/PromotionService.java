package com.profi.promoservice.service;


import com.profi.promoservice.dto.ParticipantRequest;
import com.profi.promoservice.dto.PrizeRequest;
import com.profi.promoservice.dto.PromotionResponse;
import com.profi.promoservice.dto.PromotionRequest;
import com.profi.promoservice.entity.Promotion;
import com.profi.promoservice.entity.RaffleResult;

import java.util.List;

public interface PromotionService {
    Long addPromotion(PromotionRequest request);

    List<PromotionResponse> getAllPromotions();

    Promotion getPromotionById(Long id);

    void updatePromotionById(Long id, PromotionRequest request);

    void deletePromotionById(Long id);

    Long addParticipantToPromotionById(Long promotionId, ParticipantRequest request);

    void deleteParticipantFromPromotionById(Long promotionId, Long participantId);

    Long addPrizeToPromotionById(Long id, PrizeRequest request);

    void deletePrizeFromPromotionById(Long promotionId, Long prizeId);

    List<RaffleResult> raffleInPromotionById(Long id);

}
