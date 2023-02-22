FROM nginx:alpine-slim

WORKDIR /todo-list-app/client

COPY index.html .
COPY styles/ ./styles
COPY js/ ./js
COPY nginx.conf /etc/nginx/conf.d/default.conf
