package config

import (
	"log"
	"os"

	"github.com/joho/godotenv"
)

type Config struct {
	DbUrl string
}

var c Config

func LoadConfig() {
	err := godotenv.Load("../.env")
	if err != nil {
		log.Fatal("Cannot load config files")
		panic("Cannot load config files")
	}
	c.DbUrl = os.Getenv("DB_URL")
}

func GetConfig() Config {
	return c
}
