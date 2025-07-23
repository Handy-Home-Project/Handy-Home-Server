package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.RoomDTO;
import com.example.handy_home.core.home.domain.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HomeGenerator {

    private final Environment env;
    private RestTemplate restTemplate;
    private String requestRoomJson(File image) {
        if(restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(env.getProperty("FAST_API_URL"), requestEntity, String.class).getBody();
    }

    public List<RoomDTO> generate(File image) throws JsonProcessingException {
//        String json requestRoomJson(image);
        String json = """
                [
                  {
                    "name": "거실",
                    "vertexes": [
                      [
                        11.047850515463919,
                        5.862556701030928
                      ],
                      [
                        11.047850515463919,
                        5.862556701030928
                      ],
                      [
                        13.833621621621623,
                        5.862556701030928
                      ],
                      [
                        13.833621621621623,
                        5.862556701030928
                      ]
                    ],
                    "type": "LIVING_ROOM"
                  },
                  {
                    "name": "내 방",
                    "vertexes": [
                      [
                        13.833621621621623,
                        5.862556701030928
                      ],
                      [
                        13.833621621621623,
                        9.127687466087899
                      ],
                      [
                        13.833621621621623,
                        9.127687466087899
                      ],
                      [
                        13.833621621621623,
                        9.127687466087899
                      ],
                      [
                        16.95618636003172,
                        9.127687466087899
                      ],
                      [
                        16.95618636003172,
                        5.862556701030928
                      ]
                    ],
                    "type": "BEDROOM"
                  },
                  {
                    "name": "부엌",
                    "vertexes": [
                      [
                        2.567388316151203,
                        5.862556701030928
                      ],
                      [
                        2.567388316151203,
                        14.173973490427098
                      ],
                      [
                        11.047850515463919,
                        14.173973490427098
                      ],
                      [
                        11.047850515463919,
                        5.862556701030928
                      ]
                    ],
                    "type": "KITCHEN"
                  },
                  {
                    "name": "작업실",
                    "vertexes": [
                      [
                        2.567388316151203,
                        0.06789690721649484
                      ],
                      [
                        2.567388316151203,
                        5.862556701030928
                      ],
                      [
                        11.047850515463919,
                        5.862556701030928
                      ],
                      [
                        11.047850515463919,
                        0.06789690721649484
                      ]
                    ],
                    "type": "BEDROOM"
                  },
                  {
                    "name": "방2",
                    "vertexes": [
                      [
                        13.833621621621623,
                        0.06789690721649484
                      ],
                      [
                        13.833621621621623,
                        5.862556701030928
                      ],
                      [
                        20.0,
                        5.862556701030928
                      ],
                      [
                        20.0,
                        0.06789690721649484
                      ]
                    ],
                    "type": "BEDROOM"
                  },
                  {
                    "name": "공용공간",
                    "vertexes": [
                      [
                        13.833621621621623,
                        5.862556701030928
                      ],
                      [
                        11.047850515463919,
                        5.862556701030928
                      ],
                      [
                        11.047850515463919,
                        14.173973490427098
                      ],
                      [
                        16.95618636003172,
                        14.173973490427098
                      ],
                      [
                        16.95618636003172,
                        9.127687466087899
                      ],
                      [
                        13.833621621621623,
                        9.127687466087899
                      ],
                      [
                        13.833621621621623,
                        5.862556701030928
                      ]
                    ],
                    "type": "ROOM"
                  },
                  {
                    "name": "서재",
                    "vertexes": [
                      [
                        16.95618636003172,
                        5.862556701030928
                      ],
                      [
                        16.95618636003172,
                        9.127687466087899
                      ],
                      [
                        20.0,
                        9.127687466087899
                      ],
                      [
                        20.0,
                        5.862556701030928
                      ]
                    ],
                    "type": "BEDROOM"
                  },
                  {
                    "name": "현관",
                    "vertexes": [
                      [
                        2.567388316151203,
                        1.366215022091311
                      ],
                      [
                        0.18382268041237115,
                        1.366215022091311
                      ],
                      [
                        0.18382268041237115,
                        14.173973490427098
                      ],
                      [
                        2.567388316151203,
                        14.173973490427098
                      ]
                    ],
                    "type": "ROOM"
                  },
                  {
                    "name": "창고",
                    "vertexes": [
                      [
                        16.95618636003172,
                        10.470735395189003
                      ],
                      [
                        16.95618636003172,
                        13.060817869415809
                      ],
                      [
                        17.67694845360825,
                        13.060817869416494
                      ],
                      [
                        17.67694845360825,
                        14.173973490427098
                      ],
                      [
                        20.0,
                        14.173973490427098
                      ],
                      [
                        20.0,
                        10.470735395189003
                      ]
                    ],
                    "type": "ROOM"
                  },
                  {
                    "name": "복도",
                    "vertexes": [
                      [
                        11.047850515463919,
                        0.06789690721649484
                      ],
                      [
                        11.047850515463919,
                        5.862556701030928
                      ],
                      [
                        13.833621621621623,
                        5.862556701030928
                      ],
                      [
                        13.833621621621623,
                        1.366215022091311
                      ],
                      [
                        13.833621621621623,
                        0.06789690721649484
                      ]
                    ],
                    "type": "ROOM"
                  },
                  {
                    "name": "창고2",
                    "vertexes": [
                      [
                        16.95618556701031,
                        9.127690721649484
                      ],
                      [
                        16.95618556701031,
                        10.470742268041239
                      ],
                      [
                        20.0,
                        10.470742268041239
                      ],
                      [
                        20.0,
                        9.127690721649484
                      ]
                    ],
                    "type": "ROOM"
                  },
                  {
                    "name": "창고3",
                    "vertexes": [
                      [
                        16.95618636003172,
                        13.060817869415809
                      ],
                      [
                        16.95618636003172,
                        14.173973490427098
                      ],
                      [
                        17.67694845360825,
                        14.173973490427098
                      ],
                      [
                        17.67694845360825,
                        13.060817869415809
                      ]
                    ],
                    "type": "ROOM"
                  }
                ]
        """ ;
        return new ObjectMapper().readValue(json, new TypeReference<List<RoomDTO>>(){});

    }
}
