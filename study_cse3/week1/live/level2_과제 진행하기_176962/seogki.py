def t_to_min(time):
    h, m = map(int, time.split(':'))
    return h * 60 + m

def solution(plans):
    answer, st = [], []
    plans = sorted(list(map(lambda x:[x[0],t_to_min(x[1]),int(x[2])], plans)), key=lambda x: x[1])
    t, i = plans[0][1], 0
    while i < len(plans):
        st.append(plans[i])
        if t < st[-1][1]:
            t = st[-1][1]
        while st and st[-1][2]:
            t += 1
            st[-1][2] -= 1
            if not st[-1][2]:
                answer.append(st.pop()[0])
            if i+1 < len(plans) and t == plans[i+1][1]:
                st.append(plans[i+1])
                i += 1
        i += 1
    return answer
