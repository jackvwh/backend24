package prog24hour.prog24hourbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import prog24hour.prog24hourbackend.controller.DisciplineController;
import prog24hour.prog24hourbackend.dto.DisciplineDto;
import prog24hour.prog24hourbackend.service.DisciplineService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DisciplineControllerTest {

    @Mock
    private DisciplineService disciplineService;

    @InjectMocks
    private DisciplineController disciplineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Should get all disciplines successfully")
    @Test
    void getAllDisciplinesSuccessfully() {
        DisciplineDto disciplineDto1 = new DisciplineDto();
        DisciplineDto disciplineDto2 = new DisciplineDto();
        List<DisciplineDto> disciplineDtoList = Arrays.asList(disciplineDto1, disciplineDto2);
        when(disciplineService.getAllDisciplines()).thenReturn(disciplineDtoList);

        ResponseEntity<List<DisciplineDto>> response = disciplineController.getAllDisciplines();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(disciplineDtoList, response.getBody());
    }

    @DisplayName("Should get discipline by id successfully")
    @Test
    void getDisciplineByIdSuccessfully() {
        DisciplineDto disciplineDto = new DisciplineDto();
        when(disciplineService.getDiscipline(anyInt())).thenReturn(disciplineDto);

        ResponseEntity<DisciplineDto> response = disciplineController.getDiscipline(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(disciplineDto, response.getBody());
    }

    @DisplayName("Should create discipline successfully")
    @Test
    void createDisciplineSuccessfully() {
        DisciplineDto disciplineDto = new DisciplineDto();
        when(disciplineService.createDiscipline(disciplineDto)).thenReturn(disciplineDto);

        ResponseEntity<DisciplineDto> response = disciplineController.createDiscipline(disciplineDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(disciplineDto, response.getBody());
    }

    @DisplayName("Should update discipline successfully")
    @Test
    void updateDisciplineSuccessfully() {
        DisciplineDto disciplineDto = new DisciplineDto();
        when(disciplineService.updateDiscipline(disciplineDto)).thenReturn(disciplineDto);

        ResponseEntity<DisciplineDto> response = disciplineController.updateDiscipline(disciplineDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(disciplineDto, response.getBody());
    }

    @DisplayName("Should delete discipline successfully")
    @Test
    void deleteDisciplineSuccessfully() {
        doNothing().when(disciplineService).deleteDiscipline(anyInt());

        ResponseEntity<Void> response = disciplineController.deleteDiscipline(1);

        assertEquals(200, response.getStatusCodeValue());
    }
}