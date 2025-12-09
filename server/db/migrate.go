package main

import (
	"log/slog"

	"aayushjoshi2709.github.com/myvid/config"
	"aayushjoshi2709.github.com/myvid/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func init() {
	config.LoadConfig()
}

func main() {
	myConfig := config.GetConfig()
	db, err := gorm.Open(postgres.Open(myConfig.DbUrl), &gorm.Config{})
	if err != nil {
		slog.Info("Error connection to the database")
	}

	db.AutoMigrate(
		&model.Channel{},
		&model.Subscription{},
		&model.User{},
		&model.Video{},
	)
}
