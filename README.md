# Gamehall API
## Entries
- "/users"
  - "/": GET: Gets all users.
    - response
      ```json
      {
        "ok": true,
        "data": {
          "users": [
            {
              "username": "userExample1",
              "password": "encryptedPassword",
              "email": "e1@mail.com"
            },
            {
              "username": "userExample2",
              "password": "encryptedPassword",
              "email": "e2@mail.com"
            }
          ]
        }
      }
      ```  
     
  - "/user/register": POST: Register a user
    - request
      ```json
      {
        "username": "string",
        "password": "string",
        "email": "e@mail.com"  
      }
      ```
    - response
      ```json
      {
        "ok": true | false,
        "data": {
          "username": "string",
          "message": "User created successfully | User not created"
        }
      }
      ```
  - "/user/login": POST: Login a user
    - request
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - response
      ```json
      {
        "ok": true | false,
        "data": {
          "user_token": "token"
        }
      }
      ```
  - "/user/update": POST: Update a user data
    - request
      ```json
      {
        "user_token": "token",
        "user": {
          "username": "string",
          "password": "string",
          "email": "e@mail.com"
        } 
      }
      ```
    - response
      ```json
      {
        "ok": true | false,
        "data": {
          "username": "username",
          "message": "User updated | Error"
        }
      }
      ```
  - "/user/{id}": GET: Get a user data
    - request
      ```json
      {
        "user_token": "token"
      }
      ```
    - response
      ```json
      {
        "ok": true | false,
        "data": {
          "user": {
            "username": "string",
            "password": "string",
            "email": "e@mail.com"
          }
        }
      }
      ```
- "/games"
  - "/": GET: Get a list of all games
    - response
      ```json
      {
        "ok": true | false,
        "games": [
          {
            "name": "string",
            "players": 2
          }
        ] 
      }
      ```
  - "/game": GET: Get a game
    - request: If the id of the game_data is empty, it creates a new game_data, if not, response the game_data.
      ```json
      {
        "game_name": "name of the game", 
        "game_data": {
          "id": "empty if is a new game"
        },
        "token": "empty if is a guest user",
      }
      ```
    - response
      ```json
      {
        "game_name": "name of the game", 
        "game_data": {
          "id": "game_id"
        },
        "ok": true | false
      }
      ```
    
      