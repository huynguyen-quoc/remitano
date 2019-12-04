package com.huynguyen.videosharing.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResponseFactoryTest {

  @Test
  void acceptedShouldReturnCorrectResponseEntityWithAcceptedStatus() {
    MockResponse mockResponse = new MockResponse("test");

    ResponseEntity<MockResponse> result = ResponseFactory.accepted(mockResponse);

    assertThat(result.getBody()).isNotNull();
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    assertThat(result.getBody().getTestField()).isEqualTo("test");
  }

  @Test
  void createdShouldReturnCorrectResponseEntityWithCreatedStatus() {
    MockResponse mockResponse = new MockResponse("test");

    ResponseEntity<MockResponse> result = ResponseFactory.created(mockResponse);

    assertThat(result.getBody()).isNotNull();
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(result.getBody().getTestField()).isEqualTo("test");
  }

  @Test
  void successShouldReturnCorrectResponseEntityWithOKStatus() {
    MockResponse mockResponse = new MockResponse("test");
    ResponseEntity<MockResponse> result = ResponseFactory.success(mockResponse);

    assertThat(result.getBody()).isNotNull();
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result.getBody().getTestField()).isEqualTo("test");
  }


  private static class MockResponse implements Serializable {

    private static final long serialVersionUID = -1672882264400029987L;
    private String testField;

    MockResponse(String testField) {
      this.testField = testField;
    }

    public String getTestField() {
      return testField;
    }
  }
}