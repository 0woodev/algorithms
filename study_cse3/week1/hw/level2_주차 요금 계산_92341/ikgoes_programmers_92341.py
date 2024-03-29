from math import ceil

def time_to_min(time):
    hour,minute = list(map(int, time.split(':')))
    return hour * 60 + minute

def get_price(record,def_time, def_price, unit_time, unit_price):
    price = 0
    stay = sum(record['OUT']) - sum(record['IN'])
    if len(record['IN']) > len(record['OUT']):
        stay += 23*60+59
    if stay > def_time:
        price += (ceil((stay-def_time)/unit_time) * unit_price)    
    price += def_price
    return price
        
def solution(fees, records):
    answer = []
    parking = dict()
    for item in records:
        time, number, inout = item.split(' ')
        minute = time_to_min(time)
        if number in parking.keys():
            parking[number][inout].append(minute)
        else: 
            parking[number] = {"IN":[minute], "OUT":[]}
    
    for key,item in parking.items():
        print(key, item)
        answer.append([key,get_price(item,*fees)])
    print(answer)
    return list(map(lambda x:x[1], sorted(answer)))