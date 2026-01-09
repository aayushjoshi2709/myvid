"use client";
import TextInput from "@/components/TextInput/TextInput";
import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import VerifiedUserOutlined from "@mui/icons-material/VerifiedUserOutlined";

import DeleteForeverSharp from "@mui/icons-material/DeleteForeverSharp";
import UserInfo from "@/common/interfaces/UserInfo";
import { s3FileUpload } from "@/common/functions/S3FileUpload";
import { PresignedUrlStorageTypes } from "@/common/interfaces/PresignedUrl";
import Image from "next/image";

const UserDetailsPage = () => {
  const isDev = process.env.NODE_ENV == "development";
  // loader state
  const [loading, setLoading] = useState<boolean>(false);

  // data to be stored
  const [userId, setUserId] = useState<string>("");
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [phoneNo, setPhoneNo] = useState<number>(0);
  const [profilePicUrl, setProfilePicUrl] = useState<string>();

  function setData(userDetails: UserInfo) {
    setUserId(userDetails.id);
    setFirstName(userDetails.firstName);
    setLastName(userDetails.lastName);
    setEmail(userDetails.email);
    setUsername(userDetails.username);
    setPhoneNo(userDetails.phoneNo);
    setProfilePicUrl(userDetails.profilePicUrl);
  }

  useEffect(() => {
    const fetchUserDetails = async () => {
      const response: Response = await fetch("/api/user/details");
      if (response.ok) {
        const userDetails: UserInfo = await response.json();
        setData(userDetails);
      }
    };
    fetchUserDetails();
  }, []);

  async function handleProfilePicUpload(
    e: React.ChangeEvent<HTMLInputElement>
  ): Promise<void> {
    const file = e.target.files?.[0];
    if (file) {
      setProfilePicUrl(
        await s3FileUpload(file, PresignedUrlStorageTypes.IMAGE)
      );
    }
  }

  async function updateUser(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);

    const body: Partial<UserInfo> = {
      email,
      username,
      phoneNo,
      firstName,
      lastName,
      profilePicUrl,
    };
    const response: Response = await fetch(`/api/user/details/${userId}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    if (response.ok) {
      const userDetails: UserInfo = await response.json();
      setData(userDetails);
      toast.success("User has been updated successfully.");
    } else {
      toast.error("Error updating user");
    }

    setLoading(false);
  }

  return (
    <div className="justify-center py-16 h-screen items-center">
      <form>
        <div className="flex items-center justify-center w-fit border-gray-50 rounded-2xl shadow-2xl border mx-auto p-4">
          <div className="p-4 py-8 w-full items-center flex-row flex-1 justify-center">
            {profilePicUrl && (
              <div className="flex justify-center mb-8">
                <Image
                  unoptimized={isDev}
                  alt="profile image"
                  src={profilePicUrl}
                  width={300}
                  height={300}
                  className="w-52 h-52 rounded-full object-cover"
                />
              </div>
            )}
            <div className="flex-row  justify-center items-center  border-r-gray-200">
              <h1 className="text-4xl text-center">Hi, {firstName}</h1>
              <p className="text-center my-2">
                Our detectives found this about you 🕵️‍♂️
              </p>
            </div>

            <main className="mt-8">
              <div className="flex gap-2">
                <div className="flex-1">
                  <TextInput
                    title="First Name"
                    inputType="text"
                    id="fname"
                    required={true}
                    value={firstName}
                    onChange={(value) => setFirstName(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <TextInput
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
                  <TextInput
                    title="Email"
                    inputType="email"
                    id="email"
                    required={true}
                    value={email}
                    onChange={(value) => setEmail(value as string)}
                  />
                </div>
                <div className="flex-1">
                  <TextInput
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
                  <TextInput
                    title="Phone No"
                    inputType="phone"
                    id="phone"
                    required={true}
                    value={phoneNo?.toString()}
                    onChange={(value) => setPhoneNo(Number(value))}
                  />
                </div>
                <div className="mb-5">
                  <label
                    htmlFor="profilePic"
                    className="font-medium text-heading"
                  >
                    Profile Pic
                  </label>
                  <input
                    onChange={handleProfilePicUpload}
                    type="file"
                    id="profilePic"
                    className="border rounded-xl border-[#bbbbbb] text-sm rounded-base w-full px-3 py-2.5 shadow-xs"
                  />
                </div>
              </div>
              <hr className="gap-2 border-gray-100 border" />
              <div className=" mt-4 flex w-full items-center justify-center gap-2 text-center">
                <div>
                  <button
                    onClick={updateUser}
                    className="border rounded-3xl text-center font-bold bg-green-500 text-white border-[#eee2e2] shadow-xl p-2 px-6"
                  >
                    <VerifiedUserOutlined />
                    {loading ? "Updating User Details" : "Update Details"}
                  </button>
                </div>
                <div>
                  <button
                    onClick={updateUser}
                    className="border rounded-3xl text-center font-bold bg-red-500 text-white border-[#eee2e2] shadow-xl p-2 px-6"
                  >
                    <DeleteForeverSharp />
                    {loading ? "Updating User Details" : "Delete Account"}
                  </button>
                </div>
              </div>
            </main>
          </div>
        </div>
      </form>
    </div>
  );
};

export default UserDetailsPage;
