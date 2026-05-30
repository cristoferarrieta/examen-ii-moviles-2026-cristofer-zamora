# Panini Support Mobile

Android Jetpack Compose proof of concept for managing Panini provider support tickets related to FIFA World Cup 2026 album distribution.

## Technologies

- Kotlin
- Jetpack Compose
- Material 3
- StateFlow
- Retrofit interface definitions for future backend integration

## Repository Structure

- `app/app/src/main/java/com/cristofer/paninisupportmobile/core`: feature flags and shared UI state
- `app/app/src/main/java/com/cristofer/paninisupportmobile/data`: DTOs, API service, mapper, and mock repository
- `app/app/src/main/java/com/cristofer/paninisupportmobile/domain`: ticket models and enums
- `app/app/src/main/java/com/cristofer/paninisupportmobile/navigation`: simple screen state navigation
- `app/app/src/main/java/com/cristofer/paninisupportmobile/ui/screens`: Compose screens
- `app/app/src/main/java/com/cristofer/paninisupportmobile/ui/components`: reusable UI components
- `app/app/src/main/java/com/cristofer/paninisupportmobile/ui/viewmodel`: screen ViewModels
- `contracts/tickets-api.yaml`: concise API contract
- `docs/technical-decisions.md`: implementation decisions

## How To Run

From the `app` directory:

```bash
./gradlew assembleDebug
```

Then run the Android app from Android Studio or install the generated debug APK on an emulator/device.

## Implemented Features

- Simulated login with basic validation
- Ticket list using `LazyColumn`
- Ticket detail screen
- Create ticket form
- Status updates: Open, In Progress, Resolved, Closed
- Priority updates: Low, Medium, High
- Reactive list updates through StateFlow
- Priority-based ordering with High tickets first
- Feature flags for ticket creation and priority updates
- Mock repository with realistic Panini provider ticket data
- Retrofit API service and DTOs prepared for future backend integration

## Technical Considerations

The PoC keeps the implementation intentionally small. Business state changes happen in ViewModels and the repository, while composables focus on layout and user interactions. The repository exposes a StateFlow so create, status, and priority changes are reflected without manual refresh.
