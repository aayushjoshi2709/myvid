"use client";
import FormInput from "@/components/FormInput/FormInput";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import VerifiedUserOutlined from "@mui/icons-material/VerifiedUserOutlined";
import UserInfo from "@/common/interfaces/UserInfo";

const UserDetailsPage = () => {
  const router = useRouter();
  // loader state
  const [loading, setLoading] = useState<boolean>(false);

  // data to be stored
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [phoneNo, setPhoneNo] = useState<number>(0);

  useEffect(() => {
    const fetchUserDetails = async () => {
      const response: Response = await fetch("/api/user/details");
      if (response.ok) {
        const userDetails: UserInfo = await response.json();
        setFirstName(userDetails.firstName);
        setLastName(userDetails.lastName);
        setEmail(userDetails.email);
        setUsername(userDetails.username);
        setPhoneNo(userDetails.phoneNo);
      }
    };
    fetchUserDetails();
  }, []);

  async function updateUser(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);

    const response: Response = await fetch("/api/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email,
        username,
        phoneNo,
        firstName,
        lastName,
      }),
    });

    if (response.ok) {
      toast.success(
        "User has been created successfully. Please Login to continue"
      );
      router.push("/login");
    } else {
      console.log(response);
      toast.error("Error creating user");
    }

    setLoading(false);
  }

  return (
    <div className="justify-center flex-1 h-screen items-center">
      <div className="flex-1 items-center justify-center m-16 w-fit border-gray-50 rounded-2xl shadow-2xl border mx-auto p-4">
        <div className="mx-auto p-4 w-full items-center flex-row flex-1 justify-center">
          <form>
            <div className="flex-row  justify-center items-center  border-r-gray-200">
              <h1 className="text-4xl text-center">My Details</h1>
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
                  <button className="border rounded-3xl text-center font-bold bg-green-500 text-white border-[#eee2e2] shadow-xl p-2 px-6">
                    <VerifiedUserOutlined />
                    {loading ? "Updating User Details" : "Update Details"}
                  </button>
                </div>
              </div>
            </main>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UserDetailsPage;
