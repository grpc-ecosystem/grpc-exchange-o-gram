FROM microsoft/dotnet:latest

COPY . /app/

WORKDIR /app
RUN dotnet restore
RUN dotnet publish

ENV ASPNETCORE_URLS http://0.0.0.0:8080
EXPOSE 8080
ENTRYPOINT ["dotnet", "exec", "/app/bin/Debug/netcoreapp1.1/publish/ExchangeOGramClient.dll"]

