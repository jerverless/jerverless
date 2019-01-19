import sys

print("input = " + raw_input())

if len(sys.argv) == 2 and sys.argv[1] == '--bar':
    print "bar!"
