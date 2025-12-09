package main

import (
	"log/slog"

	"aayushjoshi2709.github.com/myvid/db"
	"aayushjoshi2709.github.com/myvid/model"
)

func main() {
	dbObj := db.New()

	slog.Info("Starting migration process...")

	dbObj.AutoMigrate(
		&model.User{},
		&model.Channel{},
		&model.Subscription{},
		&model.Video{},
	)

	slog.Info("Migration process completed successfully...")
}
