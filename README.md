# FULL_STACK_EXP10 - WebSocket Chat (Spring Boot + React)
<img width="664" height="648" alt="Screenshot 2026-04-23 at 14 26 57" src="https://github.com/user-attachments/assets/cc07eb7b-cee8-4f6a-b3d3-7c18034dba1e" />
<img width="651" height="666" alt="Screenshot 2026-04-23 at 14 27 05" src="https://github.com/user-attachments/assets/4e98d9a7-41dc-4869-a729-4e5e8c3a590d" />

A full-stack real-time chat application using:
- Spring Boot (WebSocket + STOMP + SockJS) for backend messaging
- React + Vite for frontend UI
- STOMP client on React to publish/subscribe chat messages

## Project Structure

- `src/main/java/com/AML3B/Demo_Websocket/config/WebSocketConfig.java`
  - WebSocket endpoint registration (`/ws`)
  - STOMP broker configuration (`/topic`, `/app`)
- `src/main/java/com/AML3B/Demo_Websocket/controller/ChatController.java`
  - Handles incoming messages at `/app/chat`
  - Broadcasts to `/topic/messages`
- `src/main/java/com/AML3B/Demo_Websocket/model/Message.java`
  - Chat payload model (`sender`, `content`)
- `client/`
  - React app (Vite)
  - STOMP + SockJS client

## Message Flow

1. Frontend publishes message to `/app/chat`
2. `ChatController` receives message in `sendMessage(...)`
3. Backend broadcasts the same message to `/topic/messages`
4. All subscribed frontend clients receive it in real time

## Prerequisites

- Java 21+
- Node.js 18+
- npm 9+
- Maven wrapper included (`mvnw`)

## Run the App

Open 2 terminals.

### 1) Start Backend (Spring Boot)

```bash
cd /Users/rishuraj/Downloads/Demo_Websocket
./mvnw spring-boot:run
```

Backend runs on `http://localhost:8080`

### 2) Start Frontend (React + Vite)

```bash
cd /Users/rishuraj/Downloads/Demo_Websocket/client
npm install
npm run dev
```

Frontend runs on a Vite port (for example `http://localhost:5174`).

## Test Chat

1. Open frontend URL in browser tab 1
2. Open the same URL in tab 2
3. Enter different usernames in each tab
4. Send messages
5. Verify both tabs receive the messages instantly

Also verify backend logs print lines like:

```text
Alice : hello
```

## Frontend Proxy

`client/vite.config.js` proxies `/ws` to backend `http://localhost:8080`.
So frontend can use relative SockJS endpoint `/ws`.

## Build and Verify

### Backend tests

```bash
cd /Users/rishuraj/Downloads/Demo_Websocket
./mvnw test -q
```

### Frontend production build

```bash
cd /Users/rishuraj/Downloads/Demo_Websocket/client
npm run build
```

## Tech Stack

- Spring Boot 4
- Spring WebSocket + STOMP
- React 19
- Vite 8
- `@stomp/stompjs`
- `sockjs-client`

## Notes

- CORS for WebSocket endpoint currently allows all origins via `setAllowedOriginPatterns("*")`.
- For production, restrict allowed origins and add authentication/authorization.
