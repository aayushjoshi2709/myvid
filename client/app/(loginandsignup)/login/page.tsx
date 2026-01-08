"use client";
import FormInput from "@/components/FormInput/FormInput";
import TopNav from "@/components/TopNav/TopNav";
import Link from "next/link";
import React, { useState } from "react";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";

const LoginPage = () => {
  const router = useRouter();
  // loader state
  const [loading, setLoading] = useState<boolean>(false);
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  async function loginUser(e: React.FormEvent) {
    e.preventDefault();

    setLoading(true);
    const response: Response = await fetch("/api/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        password,
        username,
      }),
    });

    if (response.ok) {
      toast.success("Login successful");
      router.push("/");
    } else {
      toast.error("Error logging in");
    }

    setLoading(false);
  }
  return (
    <div className="justify-center flex-1 h-screen items-center">
      <TopNav />
      <div className="flex-1 items-center justify-center">
        <div className="mx-auto mt-16 p-4 w-1/3 items-center flex-row flex-1 justify-center">
          <form>
            <div className="flex-row  justify-center items-center  border-r-gray-200">
              <h1 className="text-4xl text-center">Login</h1>
            </div>
            <main className="mt-8">
              <div className="flex-row gap-1">
                <div className="flex-1">
                  <FormInput
                    title="Username"
                    inputType="username"
                    id="username"
                    required={true}
                    value={username}
                    onChange={(value) => setUsername(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <FormInput
                    title="Password"
                    inputType="text"
                    id="Password"
                    placeHolder="Enter the password"
                    value={password}
                    onChange={(value) => setPassword(value as string)}
                    required={true}
                  />
                </div>
              </div>
              <div className="mt-10 flex w-full items-center justify-center gap-2 text-center">
                <div>
                  <input
                    className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
                    type="button"
                    value={loading ? "Logging In ..." : "Login"}
                    onClick={loginUser}
                  />
                </div>
                <div>
                  <Link href={"/signup"}>
                    <input
                      className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
                      type="button"
                      value="Signup"
                    />
                  </Link>
                </div>
              </div>
            </main>
          </form>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
