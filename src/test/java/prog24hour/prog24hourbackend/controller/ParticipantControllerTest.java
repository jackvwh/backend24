package prog24hour.prog24hourbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import prog24hour.prog24hourbackend.dto.ParticipantDto;
import prog24hour.prog24hourbackend.service.ParticipantService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Should create participant successfully")
    @Test
    void createParticipantSuccessfully() {
        ParticipantDto participantDto = new ParticipantDto();
        when(participantService.createParticipant(participantDto)).thenReturn(participantDto);

        ResponseEntity<ParticipantDto> response = participantController.createParticipant(participantDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(participantDto, response.getBody());
    }

    @DisplayName("Should get all participants successfully")
    @Test
    void getParticipantsSuccessfully() {
        ParticipantDto participantDto1 = new ParticipantDto();
        ParticipantDto participantDto2 = new ParticipantDto();
        List<ParticipantDto> participantDtoList = Arrays.asList(participantDto1, participantDto2);
        when(participantService.getAllParticipants()).thenReturn(participantDtoList);

        ResponseEntity<Iterable<ParticipantDto>> response = participantController.getParticipants();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(participantDtoList, response.getBody());
    }

    @DisplayName("Should get participant by id successfully")
    @Test
    void getParticipantByIdSuccessfully() {
        ParticipantDto participantDto = new ParticipantDto();
        when(participantService.getParticipant(anyInt())).thenReturn(participantDto);

        ResponseEntity<ParticipantDto> response = participantController.getParticipant(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(participantDto, response.getBody());
    }

    @DisplayName("Should update participant successfully")
    @Test
    void updateParticipantSuccessfully() {
        ParticipantDto participantDto = new ParticipantDto();
        when(participantService.updateParticipant(anyInt(), any(ParticipantDto.class))).thenReturn(participantDto);

        ResponseEntity<ParticipantDto> response = participantController.updateParticipant(1, participantDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(participantDto, response.getBody());
    }

    @DisplayName("Should delete participant successfully")
    @Test
    void deleteParticipantSuccessfully() {
        doNothing().when(participantService).deleteParticipant(anyInt());

        ResponseEntity<String> response = participantController.deleteParticipant(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Participant deleted", response.getBody());
    }
}