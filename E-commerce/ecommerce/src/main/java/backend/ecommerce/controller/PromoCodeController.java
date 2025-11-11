package backend.ecommerce.controller;

import backend.ecommerce.model.PromoCode;
import backend.ecommerce.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/promocodes")
@CrossOrigin(origins = "http://localhost:3000")
public class PromoCodeController {

    @Autowired
    private PromoCodeService promoCodeService;

    @PostMapping("/validate/{code}")
    public ResponseEntity<?> validatePromoCode(@PathVariable String code) {
        try {
            PromoCode promoCode = promoCodeService.validatePromoCode(code);
            return ResponseEntity.ok(promoCode);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid or expired promo code");
        }
    }

    @GetMapping
    public List<PromoCode> getAllPromoCodes() {
        return promoCodeService.getAllPromoCodes();
    }

    @PostMapping
    public PromoCode createPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeService.createPromoCode(promoCode);
    }
}