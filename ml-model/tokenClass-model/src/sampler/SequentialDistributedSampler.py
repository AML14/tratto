import torch
from torch.utils.data import Sampler


class SequentialDistributedSampler(Sampler):
    def __init__(self, dataset):
        self.dataset = dataset
        self.num_samples = len(dataset)
        self.rank = torch.distributed.get_rank() if torch.distributed.is_initialized() else 0
        self.world_size = torch.distributed.get_world_size() if torch.distributed.is_initialized() else 1

    def __iter__(self):
        indices = list(range(self.rank, self.num_samples, self.world_size))
        return iter(indices)

    def __len__(self):
        return self.num_samples