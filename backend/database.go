package main

import (
	"math/rand"
	"strconv"
	"time"
    "database/sql"
    "fmt"
    _ "github.com/lib/pq"
)

var db *sql.DB

// WARNING: THE PASSWORD IS STORED IN PLAIN-TEXT IN THE DATABASE WHICH IS HIGHLY INSECURE
// MUST BE CHANGED BEFORE THE BACKEND GETS RELEASED TO THE PUBLIC
func AddDataToDatabase(student_data students_database) bool {
	insertdata := `insert into "students_data"("roll_no", "name", "hostel_code", "password") values($1, $2, $3, $4)`
    _, err := db.Exec(insertdata, student_data.Roll_No, student_data.Name, student_data.Hostel_Code, student_data.Password)
    if err != nil {
		panic(err)
		return false
	}

    Students_Data = append(Students_Data, student_data)
    return true
}

func AddComplaintToDatabase(complaint_data students_complaint) bool {
	// Randomly generate the unique id of the complaint
	rand.Seed(time.Now().UnixNano())
	complaint_data.Uid = strconv.Itoa(rand.Intn(10000000 - 1 + 1) + 1)

	insertdata := `insert into "complaint_data" values($1, $2, $3, $4)`
    _, err := db.Exec(insertdata, complaint_data.Uid, complaint_data.Complaint_Text, complaint_data.Complaint_Text_Title, complaint_data.Roll_No, complaint.hostel_code)
    if err != nil {
		panic(err)
		return false
	}
	return true
}

func CheckUsernameAndPassword (username string, password string) bool {
	// WARNING: THE PASSWORD IS STORED IN PLAIN-TEXT IN THE DATABASE WHICH IS HIGHLY INSECURE
	// MUST BE CHANGED BEFORE THE BACKEND GETS RELEASED TO THE PUBLIC
	var pass string
	query := fmt.Sprintf("SELECT password from admin_data WHERE username='%s'", username)
	rows, err := db.Query(query)
	if err != nil {
		panic(err)
		return false
	}
	defer rows.Close()

	// First check whether the roll no. exists in the database
	rows.Next()
	err = rows.Scan(&pass)
	if err != nil {
		panic(err)
		return false
	}
	if pass == "" {
		return false
	} else if password != pass {
		return false
	}
	return true
}

func CheckRollNoAndPassword (roll_no string, password string) bool {
	// WARNING: THE PASSWORD IS STORED IN PLAIN-TEXT IN THE DATABASE WHICH IS HIGHLY INSECURE
	// MUST BE CHANGED BEFORE THE BACKEND GETS RELEASED TO THE PUBLIC
	var pass string
	query := fmt.Sprintf("SELECT password from students_data WHERE roll_no='%s'", roll_no)
	rows, err := db.Query(query)
	if err != nil {
		panic(err)
		return false
	}

	defer rows.Close()

	// First check whether the roll no. exists in the database
	rows.Next()
	err = rows.Scan(&pass)
	if err != nil {
		panic(err)
		return false
	}

	if pass == "" {
		return false
	} else if password != pass {
		return false
	}
	return true
}

func GatherDataFromDatabase() bool {
	var admin_data admin_database
	var students_data students_database
	var student_complaint_data students_complaint

	rows, err := db.Query("SELECT username, name, hostel_code from admin_data")
	if err != nil {
		panic(err)
		return false
	}

	defer rows.Close()
	for rows.Next() {
		err = rows.Scan(&admin_data.Username, &admin_data.Name, &admin_data.Hostel_Code)
		if err != nil {
			panic(err)
			return false
		}
		Admin_Data = append(Admin_Data, admin_data)
	}

	rows, err = db.Query("SELECT roll_no, name, hostel_code from students_data")
	if err != nil {
		panic(err)
		return false
	}
	defer rows.Close()
	for rows.Next() {
		err = rows.Scan(&students_data.Roll_No, &students_data.Name, &students_data.Hostel_Code)
		if err != nil {
			panic(err)
			return false
		}
		Students_Data = append(Students_Data, students_data)
	}

	rows, err = db.Query("SELECT * from complaint_data")
	if err != nil {
		panic(err)
		return false
	}
	defer rows.Close()
	for rows.Next() {
		err = rows.Scan(&student_complaint_data.Uid, &student_complaint_data.Complaint_Text, &student_complaint_data.Complaint_Text_Title, &student_complaint_data.Roll_No)
		if err != nil {
			panic(err)
			return false
		}
		Complaint_Data = append(Complaint_Data, student_complaint_data)
	}
	return true
}

func UserComplaintResolve(query_resolve string, uid string) bool {
	insertdata := `update "complaint_data" set "query_resolved" = $1 where uid = $2' `
    _, err := db.Exec(insertdata, query_resolve, uid)
    if err != nil {
		panic(err)
		return false
	}
	return true
}

func OpenDatabase() bool {
	var err error
	psqlconn := fmt.Sprintf("host=localhost port=5432 user=postgres password=12345 dbname=main_data sslmode=disable")
	db, err = sql.Open("postgres", psqlconn)
	if err != nil {
		panic(err)
		return false
	}

	err = db.Ping()
    if err != nil {
		panic(err)
		return false
	}
	
	fmt.Println("Database Connected!")
	return true
}
