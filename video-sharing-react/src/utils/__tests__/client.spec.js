import * as axios from "axios";

import { ServerError, parseError, request } from "utils/client";

jest.mock("axios");

describe("client", () => {
  beforeEach(() => {});

  describe("request", () => {
    it("should fail without param", () => {
      expect(() => request()).toThrow("Error! You must pass `url`");
    });

    it("should fail to a POST without payload", () => {
      expect(() =>
        request("http://example.com/token", { method: "POST" })
      ).toThrow("Error! You must pass `payload`");
    });

    it("should execute a GET successfully", done => {
      axios.request.mockImplementation(() =>
        Promise.resolve({
          data: JSON.stringify({ hello: "world" }),
          ...global.fetchInit
        })
      );
      request("http://example.com/token").then(data => {
        expect(data).toMatchSnapshot();
        done();
      });
    });

    it("should execute a POST successfully", done => {
      axios.request.mockImplementation(() =>
        Promise.resolve({
          data: null,
          status: 201,
          statusText: "Created",
          ...global.fetchInit
        })
      );
      request("http://example.com/token", {
        method: "POST",
        payload: { a: 1 }
      }).then(data => {
        expect(data).toMatchSnapshot();
        done();
      });
    });

    it("should reject for a  bad request", done => {
      const err = new Error();
      err.response = {
        data: { error: "FAILED" },
        status: 400,
        statusText: "Invalid",
        ...global.fetchInit
      };
      axios.request.mockImplementation(() => Promise.reject(err));
      request("http://example.com/token").catch(error => {
        expect(error.response).toEqual({ error: "FAILED" });
        expect(error.status).toBe(400);
        done();
      });
    });
  });

  describe("ServerError", () => {
    it("should create a new instance", () => {
      const error = new ServerError("error");
      expect(error.name).toBe("ServerError");
    });
  });

  describe("parseError", () => {
    it("should handle messages", () => {
      expect(parseError("Failed")).toBe("Failed");
      expect(parseError()).toBe("Something went wrong");
    });
  });
});
