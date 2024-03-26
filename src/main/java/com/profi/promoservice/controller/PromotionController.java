package com.profi.promoservice.controller;

import com.profi.promoservice.dto.ParticipantRequest;
import com.profi.promoservice.dto.PrizeRequest;
import com.profi.promoservice.dto.PromotionRequest;
import com.profi.promoservice.dto.PromotionResponse;
import com.profi.promoservice.entity.Promotion;
import com.profi.promoservice.entity.RaffleResult;
import com.profi.promoservice.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/promo")
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Long addPromotion(@Valid @RequestBody PromotionRequest request){
        return promotionService.addPromotion(request);
    }

    @GetMapping
    public List<PromotionResponse> getAllPromotions(){
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public Promotion getGroupById(@PathVariable Long id){
        return promotionService.getPromotionById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePromotionById(@PathVariable Long id, @Valid @RequestBody PromotionRequest request){
        promotionService.updatePromotionById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePromotionById(@PathVariable Long id){
        promotionService.deletePromotionById(id);
    }

    @PostMapping("/{id}/participant")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addParticipantToPromotionById(@PathVariable Long id, @Valid @RequestBody ParticipantRequest request){
        return promotionService.addParticipantToPromotionById(id, request);
    }

    @DeleteMapping("/{promotionId}/participant/{participantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipantFromPromotionById(@PathVariable Long promotionId, @PathVariable Long participantId){
        promotionService.deleteParticipantFromPromotionById(promotionId, participantId);
    }

    @PostMapping("/{id}/prize")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addPrizeToPromotionById(@PathVariable Long id, @Valid @RequestBody PrizeRequest request){
        return promotionService.addPrizeToPromotionById(id, request);
    }

    @DeleteMapping("/{promotionId}/prize/{prizeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrizeFromPromotionById(@PathVariable Long promotionId, @PathVariable Long prizeId){
        promotionService.deletePrizeFromPromotionById(promotionId, prizeId);
    }

    @PostMapping("/{id}/raffle")
    public List<RaffleResult> raffleInPromotionById(@PathVariable Long id){
        return promotionService.raffleInPromotionById(id);
    }
}
