"use client";
import FormInput from "@/components/FormInput/FormInput";
import TopNav from "@/components/TopNav/TopNav";
import Link from "next/link";
const LoginPage = () => {
  function uploadVedio() {}
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
                  />
                </div>
                <div className="flex-1">
                  <FormInput
                    title="Password"
                    inputType="text"
                    id="Password"
                    placeHolder="Enter the password"
                    required={true}
                  />
                </div>
              </div>
              <div className="mt-10 flex w-full items-center justify-center gap-2 text-center">
                <div>
                  <input
                    className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
                    type="button"
                    value="Login"
                    onClick={uploadVedio}
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
