import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import SidePanel from "@/components/SidePanel/SidePanel";
import TopNav from "@/components/TopNav/TopNav";
const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Myvid",
  description: "Your vedio sharing tool",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <TopNav />

        <div className="display flex h-screen p-0 m-0 w-screen">
          <SidePanel />
          <div className="flex-1 h-full">{children}</div>
        </div>
      </body>
    </html>
  );
}
