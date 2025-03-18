#!/bin/bash

# Get the active window ID
WINDOW_ID=$(xdotool getactivewindow)

# Get the window name
WINDOW_NAME=$(xdotool getwindowname "$WINDOW_ID")

# Get the PID of the process owning the window
PID=$(xdotool getwindowpid "$WINDOW_ID")

# Get the process name
PROCESS_NAME=$(ps -p "$PID" -o comm=)

# Output the results
#echo "Window ID: $WINDOW_ID"
#echo "Window Name: $WINDOW_NAME"
#echo "Process Name: $PROCESS_NAME"
#echo "PID: $PID"

echo $PROCESS_NAME:$WINDOW_NAME