
def time_to_min(time):
    hour,minute = list(map(int, time.split(':')))
    return hour * 60 + minute

def solution(book_time):
    room = []
    for start, end in sorted(book_time):
        start, end = time_to_min(start), time_to_min(end)
        for i,val in enumerate(room):
            if val <= start:
                room[i] = end+10
                break
        else:
            room.append(end+10)
    return len(room)