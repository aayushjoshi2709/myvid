import { cookies } from "next/headers";
import { NextResponse } from "next/server";

export async function GET() {
  const cookie = await cookies()
  const token = cookie.get("accessToken");

  if (!token) {
    return NextResponse.json({ loggedIn: false }, { status: 401 });
  }
  return NextResponse.json({ loggedIn: true });
}