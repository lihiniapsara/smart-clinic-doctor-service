# Doctor-Service

A microservice responsible for managing doctor slots and doctor profile descriptors. It exposes a REST JSON API consumed by the API Gateway.

## Tech Stack

| Technology | Details |
|---|---|
| Java | 25 |
| Spring Boot | 4.0.3 |
| Spring Cloud | 2025.1.0 |
| Spring Data MongoDB | Persistence layer |
| MongoDB | Document database (port `13500`) |
| MapStruct | DTO <-> Document mapping |
| Spring Validation | Bean validation |
| Eureka Client | Service discovery |
| Config Client | Externalized configuration |

## Service Details

| Property | Value |
|---|---|
| Port | `8001` |
| Artifact ID | `Doctor-Service` |
| Group ID | `lk.ijse.eca` |
| Base Path | `/api/v1/doctors` |

## API Endpoints

| Method | Path | Description | Content-Type |
|---|---|---|---|
| `POST` | `/api/v1/doctors` | Create a doctor slot | `application/json` |
| `GET` | `/api/v1/doctors` | Get all doctor slots | — |
| `GET` | `/api/v1/doctors/{doctorSlotId}` | Get a doctor slot by ID | — |
| `PUT` | `/api/v1/doctors/{doctorSlotId}` | Update a doctor slot | `application/json` |
| `DELETE` | `/api/v1/doctors/{doctorSlotId}` | Delete a doctor slot | — |

## Sample Request

```json
{
  "doctorSlotId": "CARD-001",
  "description": "Cardiology Consultation"
}
```

## Startup Order

1. Config-Server (`9000`)
2. Service-Registry (`9001`)
3. Api-Gateway (`7000`)
4. Doctor-Service (`8001`)
