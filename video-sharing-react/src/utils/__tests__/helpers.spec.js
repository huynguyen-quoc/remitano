import { parseYoutubeId } from "utils/helpers";

describe("Helpers", () => {
  it("parseYoutubeId should return correct id", () => {
    const result = parseYoutubeId(
      "https://www.youtube.com/watch?v=EGhSY00ouCk"
    );
    expect(result).toEqual("EGhSY00ouCk");
  });
  it("parseYoutubeId should return empty when url not correct", () => {
    const result = parseYoutubeId("https://www.youtube.com/watch?v=EGhSY00oCk");
    expect(result).toEqual("");
  });
});
