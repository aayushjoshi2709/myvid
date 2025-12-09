package db

import (
	"log/slog"

	"aayushjoshi2709.github.com/myvid/config"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func init() {
	config.LoadConfig()
}

var dbObj *gorm.DB

func New() *gorm.DB {
	if dbObj != nil {
		return dbObj
	}
	myConfig := config.GetConfig()
	var err error
	dbObj, err = gorm.Open(postgres.Open(myConfig.DbUrl), &gorm.Config{})
	if err != nil {
		slog.Error("Error connection to the database")
		panic("Error connection to the database")
	}
	return dbObj
}
