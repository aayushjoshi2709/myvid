import { cookies } from "next/headers";

export class ApiClient<T> {
  #baseUrl: string = "";
  constructor(baseUrl: string) {
    this.#baseUrl = baseUrl;
  }

  async #buildHeader(): Promise<HeadersInit> {
    const headers: { [key: string]: string } = {
      "Content-Type": "application/json",
    };

    const cookieStore = await cookies();
    const token: string | undefined = cookieStore.get("accessToken")?.value;
    if (token) {
      headers["Authorization"] = `Bearer ${token}`;
    }
    return headers;
  }

  async post(endpoint: string, body: object | null): Promise<T> {
    const headers = await this.#buildHeader();
    const res: Response = await fetch(`${this.#baseUrl}${endpoint}`, {
      method: "POST",
      headers,
      body: body ? JSON.stringify(body) : null,
    });

    if (!res.ok) {
      console.log(res)
      throw new Error("An error occoured");
    }
    const data: T = await res.json();
    return data;
  }

  async get(endpoint: string): Promise<T> {
    const headers = await this.#buildHeader();
    const res: Response = await fetch(`${this.#baseUrl}${endpoint}`, {
      headers,
    });
    if (!res.ok) {
      throw new Error("An error occoured");
    }
    const data: T = await res.json();
    return data;
  }

  async put(endpoint: string, body: object | null): Promise<T> {
    const headers = await this.#buildHeader();
    const res: Response = await fetch(`${this.#baseUrl}${endpoint}`, {
      method: "PUT",
      headers,
      body: body ? JSON.stringify(body) : null,
    });

    if (!res.ok) {
      console.log(res)
    }
    const data: T = await res.json();
    return data;
  }
}
