"use client";
import FormInput from "@/components/FormInput/FormInput";
import TopNav from "@/components/TopNav/TopNav";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";
import toast from "react-hot-toast";

const SignUpPage = () => {

  const router = useRouter();
  // loader state
  const [loading, setLoading] = useState<boolean>(false);

  // data to be stored
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [phoneNo, setPhoneNo] = useState<number>(0);

  async function createUser(e: React.FormEvent) {
    e.preventDefault();
    if (confirmPassword != password) {
      return;
    }

    setLoading(true);

    const response: Response = await fetch("/api/user/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email,
        password,
        username,
        phoneNo,
        firstName,
        lastName,
      }),
    });


    if (response.ok) {
      toast.success("User has been created successfully. Please Login to continue");
      router.push("/login");
    } else {
      console.log(response);
      toast.error("Error creating user");
    }

    setLoading(false);
  }

  return (
    <div className="justify-center flex-1 h-screen items-center">
      <TopNav />
      <div className="flex-1 items-center justify-center">
        <div className="mx-auto mt-16 p-4 w-1/2 items-center flex-row flex-1 justify-center">
          <form>
            <div className="flex-row  justify-center items-center  border-r-gray-200">
              <h1 className="text-4xl text-center">Sign Up</h1>
            </div>
            <main className="mt-8">
              <div className="flex gap-2">
                <div className="flex-1">
                  <FormInput
                    title="First Name"
                    inputType="text"
                    id="fname"
                    required={true}
                    value={firstName}
                    onChange={(value) => setFirstName(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <FormInput
                    title="Last Name"
                    inputType="text"
                    id="lname"
                    required={true}
                    value={lastName}
                    onChange={(value) => setLastName(value as string)}
                  />
                </div>
              </div>

              <div className="flex gap-2">
                <div className="flex-1">
                  <FormInput
                    title="Email"
                    inputType="email"
                    id="email"
                    required={true}
                    value={email}
                    onChange={(value) => setEmail(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <FormInput
                    title="Username"
                    inputType="text"
                    id="username"
                    placeHolder="Enter the username"
                    required={true}
                    value={username}
                    onChange={(value) => setUsername(value as string)}
                  />
                </div>
              </div>
              <div className="flex gap-2">
                <div className="flex-1">
                  <FormInput
                    title="Password"
                    inputType="text"
                    id="Password"
                    placeHolder="Enter the password"
                    required={true}
                    value={password}
                    onChange={(value) => setPassword(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <FormInput
                    title="Confirm Password"
                    inputType="text"
                    id="Confirm Password"
                    placeHolder="Enter the password again"
                    required={true}
                    value={confirmPassword}
                    onChange={(value) => setConfirmPassword(value as string)}
                  />
                </div>
              </div>

              <div className="flex gap-2">
                <div className="flex-1">
                  <FormInput
                    title="Phone No"
                    inputType="phone"
                    id="phone"
                    required={true}
                    value={phoneNo?.toString()}
                    onChange={(value) => setPhoneNo(Number(value))}
                  />
                </div>
              </div>
              <div className="mt-5  flex w-full items-center justify-center gap-2 text-center">
                <div>
                  <input
                    className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
                    type="button"
                    value={loading ? "Creating user..." : "Create User"}
                    onClick={createUser}
                  />
                </div>
                <div>
                  <Link href={"/login"}>
                    <input
                      className="border rounded-2xl bg-[#eaeaea] border-[#bbbbbb] p-1 px-6"
                      type="button"
                      value="Login"
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

export default SignUpPage;
