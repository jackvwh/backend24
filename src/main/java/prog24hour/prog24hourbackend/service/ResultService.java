package prog24hour.prog24hourbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prog24hour.prog24hourbackend.repository.ResultRepository;

@Slf4j
@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

}
