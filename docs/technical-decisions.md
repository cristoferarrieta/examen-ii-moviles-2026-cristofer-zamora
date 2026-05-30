# Technical Decisions

## MVVM

The app uses a simple MVVM structure because it keeps composables focused on UI layout and user events. ViewModels hold screen state and call the repository for ticket operations, which makes the demo easy to explain without adding unnecessary architecture layers.

## Mock Repository

`MockTicketRepository` keeps provider support tickets in memory. This is enough for the exam PoC because the app does not need persistence or a real backend, but the repository interface keeps the app ready to swap in a network-backed implementation later.

## Event-Based Communication

The repository owns a `MutableStateFlow<List<Ticket>>` and exposes it as `StateFlow<List<Ticket>>`. Ticket list and detail ViewModels observe that flow, so updates are pushed to the UI automatically.

## Ticket Creation

When a ticket is saved, the create ViewModel validates required fields and calls `repository.createTicket(...)`. The repository appends the new ticket to the flow, and the list screen receives the updated list immediately.

## Priority Reordering

`TicketListViewModel` sorts tickets by priority using High, Medium, then Low. When the detail screen changes a ticket priority, the repository updates the flow and the list ViewModel emits the reordered list.

## Feature Flags

Feature flags live in `core/featureflags/FeatureFlags.kt`. `enableTicketCreation` controls the create ticket FAB, and `enablePriorityUpdate` controls whether the priority selector appears in the detail screen.

## Future Backend Integration

The data layer includes DTOs, a mapper, and a Retrofit `TicketApiService` with the endpoints from `contracts/tickets-api.yaml`. The app still uses mock data, but the networking boundary is prepared.

## Avoiding Extra Complexity

The app intentionally avoids Room, Firebase, dependency injection, remote config, and complex navigation frameworks. Manual navigation and in-memory StateFlow are enough for the required programmed behavior.
