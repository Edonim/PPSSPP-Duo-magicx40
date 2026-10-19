# Single-screen portrait mode (MagicX Zero 40)

This branch explores adapting PPSSPP-Duo for a single portrait-oriented display.

## Approach

1. Preserve the normal second-display mode as the default.
2. Add an opt-in single-screen mode for portrait handhelds.
3. Keep the game rendering surface in the upper region and host the Duo panel in the lower region.
4. Reuse the existing Duo UI and mods where possible rather than duplicating their implementation.
5. Verify both Java GL and non-GL rendering paths, lifecycle and touch/controller input.

## Important implementation notes

- The current emulator activity sets either `NativeGLSurfaceView` or `NativeSurfaceView` as the activity content view.
- The Duo interface is currently built by `DuoPresentation`, an Android `Presentation` tied to a separate `Display`.
- A safe implementation needs to separate the Duo UI's root view from the Presentation/window lifecycle, then embed that root in the activity's portrait layout.
- Keep the existing dual-display behavior intact and make single-screen mode opt-in until tested on device.
- Do not treat a successful compile as proof that the GL surface sizing, controls, touch events or Duo mods work correctly.

## Initial milestone

Create a minimal single-screen portrait layout with a resizable game surface above and a placeholder Duo panel below. Build and test on the MagicX Zero 40 before integrating the existing tab bar and mods.
