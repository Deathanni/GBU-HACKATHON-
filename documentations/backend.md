## Backend Workings
The backend of this project is written in Go using Gin framework which utilizes REST API for communication with the frontend.
There are two types of APIs in this backend:
- Public APIs which are used in login system.
- Private APIs which can be accessed only when a user/admin (warden) logs in and itself is also of two types.

## Available Public APIs
- `/login`: Used for logging in for normal users. For admins use `/admin/login`. Returns `{"token":<value>}` when login is successful. Provide json data as {"roll_no":<value>, "password"=<value>} for regular users and `{"username":<value>, "password"=<value>}` for admin users.
- `/logout`: Used for logging out for normal users. For admins use `/admin/logout`. Removes the generated token from the server and returns `StatusOK` when successful. Provide json data as `{"roll_no":<value>, "token"=<value>}` for regular users and `{"username":<value>, "token"=<value>}` for admin users.
- `/signin`: Used for signing in only for regular users. Retruns `StatusOK` when successful. Provide json data as `{"roll_no":<value>, "password":<value>, "name":<value>, "hostel_code":<value>,}`.

## Available Private APIs
These can be accessed only when the authentication is successful through token which the client gets after logging in. Specific APIs are made for both students as well as admins.

### For regular users:
For authentication, the client must provide json data as: `{"roll_no":<value>, "token"=<value>}` while accessesing these:
- `/private/user`: Used to gather information of the logged in user. Returns data as `{"roll_no":<value>, "name":<value>, "hostel_code":<value>,}`.
- `/private/user/complaint`: Used to send your complaint to the server. Returns `StatusOK` when successful. Provide json data as `{"roll_no":<value>, "complaint_text":<value>, "complaint_text_title":<value>, "hostel_code":<value>}`.
- `/private/complaints`: Used to gather all of complaints submitted by the logged in user. Returns json data as `{"roll_no":<value>, "complaint_text":<value>, "complaint_text_title":<value>, "hostel_code":<value>}`.
- `/complaint/:uid/resolve`: Used to resolve a specific complaint query identified with its unique id. Returns `StatusOK` when successful. Provide uid as `/complaint/<value>/resolve`

### For admin users:
For authentication, the client must provide json data as: `{"username":<value>, "token"=<value>}` while accessesing these:
- `/admin/admin_user`: Used to gather information of the logged in user. Returns data as `{"username":<value>, "name":<value>, "hostel_code":<value>,}`.
- `/admin/users/:hostel_code`: Used to gather information of all logged in students of the hostel the warden is of. Returns data as `{"roll_no":<value>, "name":<value>, "hostel_code":<value>,}`. Provide hostel_code as `/admin/users/<value>`
- `/admin/complaints/:hostel_code`: Used to gather complaints of all logged in students of the hostel the warden is of. Returns data as `{"roll_no":<value>, "complaint_text":<value>, "complaint_text_title":<value>, "hostel_code":<value>}`. Provide hostel_code as `/admin/users/<value>`
- `/admin/complaint/:uid/resolve`: Used to resolve a specific complaint query identified with its unique id. Returns `StatusOK` when successful. Provide uid as `/complaint/<value>/resolve`
