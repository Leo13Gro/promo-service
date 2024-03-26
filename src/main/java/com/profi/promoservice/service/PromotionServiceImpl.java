package com.profi.promoservice.service;

import com.profi.promoservice.dto.ParticipantRequest;
import com.profi.promoservice.dto.PrizeRequest;
import com.profi.promoservice.dto.PromotionResponse;
import com.profi.promoservice.dto.PromotionRequest;
import com.profi.promoservice.entity.Participant;
import com.profi.promoservice.entity.Prize;
import com.profi.promoservice.entity.Promotion;
import com.profi.promoservice.entity.RaffleResult;
import com.profi.promoservice.exception.custom.RaffleException;
import com.profi.promoservice.repository.ParticipantRepository;
import com.profi.promoservice.repository.PrizeRepository;
import com.profi.promoservice.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PrizeRepository prizeRepository;
    private final ParticipantRepository participantRepository;


    @Override
    public Long addPromotion(PromotionRequest request) {
        Promotion promotion = new Promotion(request.getName(), request.getDescription());
        return promotionRepository.save(promotion).getId();
    }

    @Override
    public List<PromotionResponse> getAllPromotions() {
        List<Promotion> promotionList = promotionRepository.findAll();
        List<PromotionResponse> promotionDTOList = new ArrayList<>();
        promotionList.forEach(promotion -> promotionDTOList.add(promotion.toDTO()));
        return promotionDTOList;
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id).orElseThrow();
    }

    @Override
    public void updatePromotionById(Long id, PromotionRequest request) {
        Promotion promotionToUpdate = promotionRepository.findById(id).orElseThrow();
        promotionToUpdate.setName(request.getName());
        promotionToUpdate.setDescription(request.getDescription());
        promotionRepository.save(promotionToUpdate);
    }

    @Override
    public void deletePromotionById(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Long addParticipantToPromotionById(Long promotionId, ParticipantRequest request) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        Participant participantToAdd = new Participant(request.getName());
        participantRepository.save(participantToAdd);

        List<Participant> participantList = promotion.getParticipants();
        participantList.add(participantToAdd);
        promotion.setParticipants(participantList);
        promotionRepository.save(promotion);

        return participantToAdd.getId();
    }

    @Override
    public void deleteParticipantFromPromotionById(Long promotionId, Long participantId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        Participant participantToDelete = participantRepository.findById(participantId).orElseThrow();

        List<Participant> participantList = promotion.getParticipants();
        participantList.remove(participantToDelete);
        promotion.setParticipants(participantList);

        participantRepository.deleteById(participantId);
        promotionRepository.save(promotion);
    }

    @Override
    public Long addPrizeToPromotionById(Long id, PrizeRequest request) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow();
        Prize prizeToAdd = new Prize(request.getDescription());
        prizeRepository.save(prizeToAdd);

        List<Prize> prizeList = promotion.getPrizes();
        prizeList.add(prizeToAdd);
        promotion.setPrizes(prizeList);
        promotionRepository.save(promotion);

        return prizeToAdd.getId();
    }

    @Override
    public void deletePrizeFromPromotionById(Long promotionId, Long prizeId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow();
        Prize prizeToDelete = prizeRepository.findById(prizeId).orElseThrow();

        List<Prize> prizeList = promotion.getPrizes();
        prizeList.remove(prizeToDelete);
        promotion.setPrizes(prizeList);

        prizeRepository.deleteById(prizeId);
        promotionRepository.save(promotion);
    }

    @Override
    public List<RaffleResult> raffleInPromotionById(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow();
        List<Prize> prizeList = promotion.getPrizes();
        List<Participant> participantList = promotion.getParticipants();
        if (prizeList.size() != participantList.size())
            throw new RaffleException();
        Collections.shuffle(prizeList);

        List<RaffleResult> raffleResultList = new ArrayList<>();
        for (int i = 0; i < participantList.size(); i++) {
            raffleResultList.add(new RaffleResult(participantList.get(i), prizeList.get(i)));
        }
        return raffleResultList;
    }
}
