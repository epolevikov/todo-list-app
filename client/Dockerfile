FROM python:3.8-slim-buster

WORKDIR /todo-list-app/client

COPY index.html .
COPY styles/ ./styles
COPY js/ ./js

CMD ["python3", "-m", "http.server", "9000"]