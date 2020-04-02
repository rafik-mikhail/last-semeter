import numpy as np
from matplotlib import pyplot as plt
from scipy import signal
from scipy.signal import butter,filtfilt, lfilter




def Implement_Notch_Filter(fs, band, freq, ripple, order, filter_type, data):
    from scipy.signal import iirfilter, lfilter
    nyq  = fs/2.0
    low  = freq - band/2.0
    high = freq + band/2.0
    low  = low/nyq
    high = high/nyq
    b, a = iirfilter(order, [low, high], rp=ripple, btype='bandstop',
                     analog=False, ftype=filter_type)
    filtered_data = lfilter(b, a, data)
    return filtered_data

def butter_lowpass(cutoff, fs, order=5):
    nyq = 0.5 * fs
    normal_cutoff = cutoff / nyq
    b, a = butter(order, normal_cutoff, btype='low', analog=False)
    return b, a

def butter_lowpass_filter(data, cutoff, fs, order=5):
    b, a = butter_lowpass(cutoff, fs, order=order)
    y = lfilter(b, a, data)
    return y


def QRS(content, windowSize):
    plt.figure(1)
    plt.plot(content[:2000])
    plt.savefig('original.png')

    fs = 256.0 
    f0 = 60.0 
    Q = 30.0  

    x = Implement_Notch_Filter(fs,1,50,0,2,"butter",content)

    y = butter_lowpass_filter(content, 45, fs, 6)

    z = butter_lowpass_filter(x, 45, fs, 6)

    plt.figure(2)
    plt.plot(x[:2000])
    plt.savefig('notch.png')

    plt.figure(3)
    plt.plot(y[:2000])
    plt.savefig('band.png')
    plt.figure(4)
    plt.plot(z[:2000])
    plt.savefig('notch_then_band.png')

    diffed = z.copy()

    for i in range(3,len(y)-2):
        diffed[i] = 1/8 * (-(z[i-2]) -(2*z[i-1]) + (2*z[i+1]) + (z[i+2]))

    plt.figure(5)
    plt.plot(diffed[:2000])
    plt.savefig('differentiated.png')

    squared = diffed ** 2

    plt.figure(6)
    plt.plot(squared[:2000])
    plt.savefig('squared.png')

    smoothed = squared.copy()
    for i in range(windowSize-1, len(smoothed)):
        sum = 0
        for j in range(1, windowSize):
            sum += squared[i-j]
        sum += squared[i]
        smoothed[i] = sum/windowSize
    
    plt.figure(7)
    plt.plot(smoothed[:2000])
    plt.savefig('smoothed.png')


    threshold = 0.012

    hey = [0]*len(smoothed)
    over = False
    values = []
    stamps = []
    for i in range(len(smoothed)):
        if smoothed[i] > threshold:
            over = True
            values.append(smoothed[i])
            stamps.append(i)
        elif over:
            max = values[0]
            pos = stamps[0]
            for q in range(len(values)):
                if values[q] > max:
                    max = values[q]
                    pos = stamps[q]
            hey[pos] = max
            over = False
            values = []
            stamps = []


    plt.figure(8)
    plt.plot(hey[:2000])
    plt.savefig('detected.png')

    done = hey.copy()
    for q in range((windowSize//2),(len(hey) - windowSize//2)):
        if(hey[q] > 0):
            pos0 = q - windowSize//2
            max = hey[pos0]
            maxPos = pos0
            for w in range(windowSize//2):
                
                if (max > hey[pos0 + w]):
                    done[pos0 + w] = 0
                else:
                    done[maxPos] = 0
                    maxPos = pos0 + w
                    max = hey[pos0 + w]

            if max > hey[q]:
                done[q] = 0;
            else:
                done[maxPos] = 0
                maxPos = q
                max = hey[q]

            for w in range(windowSize//2):
                if (max > hey[q + 1 + w]):
                    done[q + 1 + w] = 0
                else:
                    done[maxPos] = 0
                    maxPos = q + 1 + w
                    max = hey[q + 1 + w]

    plt.figure(9)
    plt.plot(done[:2000])
    plt.savefig('detected2.png')

    timeStamps = []
    for pla in range(len(done)):
        if done[pla] > 0:
            timeStamps.append(pla)
    
    # print(len(timeStamps))

    timeIntervals = [0] * (len(timeStamps)-1)
    # print(len(timeIntervals))
    # print(len(timeStamps)-1)
    for bva in range(len(timeStamps)-1):
        timeIntervals[bva] = timeStamps[bva+1] - timeStamps[bva]
        
    

    plt.figure(10)
    plt.plot(timeIntervals)
    plt.savefig('intervals.png')


    plt.figure(11)
    plt.plot(timeStamps)
    plt.savefig('stamps.png')
    # sw = []
    # for something in range(len(hey)):
    #     if(hey[something] == 1):
    #         # print(something)
    #         sw.append(something)
    # for something in range(1,len(sw)):
    #     if(sw[something]-sw[something-1]) < 200:
    #         print(sw[something]-sw[something-1], something)

    # print("len(sw)", len(sw))

    # sw = []
    # for something in range(len(done)):
    #     if(done[something] == 1):
    #         # print(something)
    #         sw.append(something)
    # for something in range(1,len(sw)):
    #     if(sw[something]-sw[something-1]) < 200:
    #         print(sw[something]-sw[something-1], something)

    # print("len(sw)", len(sw))



    # quacker = 0
    # quaaa = 0
    # for quack in done:
        
    #     if quack > 0 and quaaa < 2000:
    #         # print("quack", quack)
    #         quacker += 1
    #         print("quacker", quacker)
    #     quaaa += 1

    plt.show()
    return 70


with open("DataN.txt", "r") as f:
    cont = f.readlines()
cont = [float(x.strip()) for x in cont]
x = QRS(cont, 5)
