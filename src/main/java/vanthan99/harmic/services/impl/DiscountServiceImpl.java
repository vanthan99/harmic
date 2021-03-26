package vanthan99.harmic.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vanthan99.harmic.converters.DiscountConverter;
import vanthan99.harmic.dto.DiscountDTO;
import vanthan99.harmic.entities.Discount;
import vanthan99.harmic.payloads.response.MessageResponse;
import vanthan99.harmic.repositories.DiscountRepository;
import vanthan99.harmic.services.DiscountService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository repo;

    @Autowired
    DiscountConverter converter;

    @Override
    public Page<DiscountDTO> findByEnableTrue(Pageable pageable) {
        return repo.findByEnableTrue(pageable).map(discount -> converter.toDTO(discount));
    }

    @Override
    public Page<DiscountDTO> findByEnableFalse(Pageable pageable) {
        return repo.findByEnableFalse(pageable).map(discount -> converter.toDTO(discount));
    }

    @Override
    public MessageResponse save(DiscountDTO dto) {
        // truong hop them moi
        if (dto.getId() == null) {
            if (!checkDateValidWhileCreate(dto.getStartDay(), dto.getEndDay())) {
                return new MessageResponse("start day or end day invalid");
            }
            repo.save(converter.toEntity(dto));
            return new MessageResponse("added discount");
        }
        // truong hop cap nhat
        else {
            // cap nhat name va note
            repo.save(converter.toEntity(dto));
            return new MessageResponse("updated discount");
        }
    }

    // check day valid while create new
    private boolean checkDateValidWhileCreate(LocalDate startDay, LocalDate endDay) {
        return !startDay.isAfter(endDay) && !startDay.isBefore(LocalDate.now());
    }

    @Override
    public MessageResponse toggleEnable(UUID uuid) {
        Discount discount = repo.findById(uuid).orElse(null);
        if (discount == null) return new MessageResponse("already exists id");
        MessageResponse message = new MessageResponse();
        if (discount.isEnable()) {
            message.setMessage("disable success!");
        } else {
            message.setMessage("enable success!");
        }
        discount.setEnable(!discount.isEnable());
        repo.save(discount);
        return message;
    }

    @Override
    public MessageResponse extendEndDay(UUID disId, LocalDate date) {
        Discount discount = repo.findById(disId).orElse(null);
        if (discount == null){
            return new MessageResponse("already exists discount");
        }
        if (discount.getStartDay().isAfter(date)){
            return new MessageResponse("end day invalid");
        }
        discount.setEndDay(date);
        repo.save(discount);
        return new MessageResponse("extend end day successfully");
    }
}
