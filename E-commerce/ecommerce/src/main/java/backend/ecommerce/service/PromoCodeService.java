package backend.ecommerce.service;

import backend.ecommerce.model.PromoCode;
import backend.ecommerce.repo.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PromoCodeService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public PromoCode validatePromoCode(String code) {
        PromoCode promoCode = promoCodeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Promo code not found"));

        if (!promoCode.getIsActive()) {
            throw new RuntimeException("Promo code is inactive");
        }

        if (promoCode.getExpiryDate() != null &&
                promoCode.getExpiryDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Promo code expired");
        }

        return promoCode;
    }

    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    public PromoCode createPromoCode(PromoCode promoCode) {
        promoCode.setCode(promoCode.getCode().toUpperCase());
        return promoCodeRepository.save(promoCode);
    }
}