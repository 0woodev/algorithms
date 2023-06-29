
def solution(gems):
	
	minleng = len(gems)
	maxleng = len(gems)
	minnum = len(gems)
	i = -1

	dict = {gem: minleng for gem in set(gems)}
	
	answer = []

	while maxleng in dict.values():
		i += 1
		dict[gems[i]] = i
		
	for maxnum in range(i, maxleng):
		dict[gems[maxnum]] = maxnum

		values = dict.values()

		if minnum not in values:
			minnum = min(values)
			leng = maxnum - minnum
			if leng < minleng:
				minleng = leng
				answer = [minnum, maxnum]

	answer[0] += 1
	answer[1] += 1

	return answer