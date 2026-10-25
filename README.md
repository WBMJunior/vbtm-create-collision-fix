# VBTM Create Collision Fix

NeoForge 1.21.1, Create 6.0.10 only. One mixin that stops the server crash
`NullPointerException: Cannot read field "x" because "mf.axis" is null` when an entity touches a moving contraption
(drills, bearings, pulleys, elevators). Create 6.0.10 nulls the manifold's `axis`/`normalAxis` in `reset()` and never
initialises them; 6.0.9 used `Vec3.ZERO`. The mixin restores `Vec3.ZERO` after construction and after each `reset()`,
which is exactly the 6.0.9 behaviour. Upstream: Creators-of-Create/Create #10218, #10278.

Pinned to Create `[6.0.10,6.0.11)`: remove this jar when Create ships the fix. Both sides. MIT.

Build: `gradle build` (ModDevGradle 2.0, Java 21) with Create 6.0.10 and its bundled Flywheel/Ponder/Registrate in
`libs/` (git-ignored).
